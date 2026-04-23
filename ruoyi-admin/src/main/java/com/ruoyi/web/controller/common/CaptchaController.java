package com.ruoyi.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;  // 关键：补充TimeUnit的导入
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.code.kaptcha.Producer;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 验证码操作处理
 *
 * @author zkz
 */
@RestController
public class CaptchaController
{
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response)
    {
        AjaxResult ajax = AjaxResult.success();
        try {
            // 1. 判断验证码是否开启
            boolean captchaEnabled = configService.selectCaptchaEnabled();
            ajax.put("captchaEnabled", captchaEnabled);
            if (!captchaEnabled)
            {
                return ajax;
            }

            // 2. 生成验证码唯一标识
            String uuid = IdUtils.simpleUUID();
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

            String capStr = null, code = null;
            BufferedImage image = null;

            // 3. 根据配置生成对应类型验证码（增加默认值，避免空指针）
            String captchaType = RuoYiConfig.getCaptchaType();
            if ("math".equals(captchaType))
            {
                String capText = captchaProducerMath.createText();
                capStr = capText.substring(0, capText.lastIndexOf("@"));
                code = capText.substring(capText.lastIndexOf("@") + 1);
                image = captchaProducerMath.createImage(capStr);
            }
            else if ("char".equals(captchaType))
            {
                capStr = code = captchaProducer.createText();
                image = captchaProducer.createImage(capStr);
            }
            else {
                // 兜底：默认使用字符验证码，避免配置错误导致空指针
                capStr = code = captchaProducer.createText();
                image = captchaProducer.createImage(capStr);
                ajax.put("warn", "验证码类型配置错误，已默认使用字符验证码");
            }

            // 4. 校验图片是否生成成功（二次兜底）
            if (image == null) {
                return AjaxResult.error("验证码图片生成失败");
            }

            // 5. 存入Redis并设置过期时间
            redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

            // 6. 转换图片为Base64（关闭流资源）
            try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                ImageIO.write(image, "jpg", os);
                ajax.put("uuid", uuid);
                ajax.put("img", Base64.encode(os.toByteArray()));
            } catch (IOException e) {
                // 捕获图片写入异常
                return AjaxResult.error("验证码图片转换失败：" + e.getMessage());
            }
        } catch (Exception e) {
            // 捕获所有未知异常，避免接口直接报错
            return AjaxResult.error("验证码生成失败：" + e.getMessage());
        }
        return ajax;
    }
}