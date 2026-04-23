<template>
  <div class="app-container">
    <!-- 搜索表单 -->
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="图书名" prop="bookName">
        <el-input
          v-model="queryParams.bookName"
          placeholder="请输入图书名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者名" prop="bookAuthor">
        <el-input
          v-model="queryParams.bookAuthor"
          placeholder="请输入作者名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图书类型" prop="typeId">
        <el-select
          v-model="queryParams.typeId"
          placeholder="请选择图书类型"
          clearable
          class="w-100"
        >
          <el-option
            v-for="item in typeOptions"
            :key="item.typeId"
            :label="item.typeName"
            :value="item.typeId"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['book:book:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['book:book:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['book:book:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['book:book:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 数据表格 -->
    <el-table v-loading="loading" :data="bookList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="图书ID" align="center" prop="bookId" />
      <el-table-column label="图书名" align="center" prop="bookName" />
      <el-table-column label="作者名" align="center" prop="bookAuthor" />
      <el-table-column label="封面" align="center" prop="bookImage" width="100">
        <template #default="scope">
          <image-preview :src="scope.row.bookImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="图书类型" align="center">
        <template #default="scope">
          {{ getTypeName(scope.row.typeId) }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['book:book:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['book:book:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 新增/修改弹窗 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bookRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="图书名" prop="bookName">
          <el-input v-model="form.bookName" placeholder="请输入图书名" />
        </el-form-item>
        <el-form-item label="作者名" prop="bookAuthor">
          <el-input v-model="form.bookAuthor" placeholder="请输入作者名" />
        </el-form-item>
        <el-form-item label="封面" prop="bookImage">
          <image-upload v-model="form.bookImage"/>
        </el-form-item>
        <el-form-item label="图书类型" prop="typeId">
          <el-select
            v-model="form.typeId"
            placeholder="请选择图书类型"
            clearable
            class="w-100"
          >
            <el-option
              v-for="item in typeOptions"
              :key="item.typeId"
              :label="item.typeName"
              :value="item.typeId"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Book">
import { ref, reactive, toRefs, getCurrentInstance } from 'vue'
import RightToolbar from '@/components/RightToolbar'
import Pagination from '@/components/Pagination'
import ImagePreview from '@/components/ImagePreview'
import ImageUpload from '@/components/ImageUpload'
import { listBook, getBook, delBook, addBook, updateBook } from "@/api/book/book"
import { listType } from "@/api/book/type"

const { proxy } = getCurrentInstance()

const bookList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  typeOptions: [],
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    bookName: null,
    bookAuthor: null,
    bookImage: null,
    typeId: null
  },
  rules: {
    bookName: [
      { required: true, message: "图书名不能为空", trigger: "blur" }
    ],
    bookAuthor: [
      { required: true, message: "作者名不能为空", trigger: "blur" }
    ],
    typeId: [
      { required: true, message: "请选择图书类型", trigger: "change" }
    ]
  }
})

const { queryParams, form, rules, typeOptions } = toRefs(data)

/** 查询图书信息列表 */
function getList() {
  loading.value = true
  listBook(queryParams.value).then(response => {
    bookList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

/** 查询图书类型列表 */
function getTypeList() {
  // 1. 先打印日志，确认函数执行了
  console.log("📢 开始请求图书类型接口...")

  // 2. 调用接口，加完整的成功/失败处理
  listType({}).then(res => {
    console.log("✅ 接口返回数据：", res) // 打印完整返回结果

    // 3. 验证返回格式是否正确
    if (res && res.rows) {
      data.typeOptions = res.rows
      console.log("📚 最终类型列表：", data.typeOptions)
    } else {
      console.error("❌ 接口返回格式错误，没有 rows 字段")
      data.typeOptions = [] // 避免报错
    }
  }).catch(err => {
    // 4. 捕获所有错误（404/403/500 等）
    console.error("❌ 请求图书类型接口失败：", err)
    data.typeOptions = []
  })
}

/** 根据typeId获取类型名称 */
function getTypeName(typeId) {
  const item = data.typeOptions.find(t => t.typeId === typeId)
  return item ? item.typeName : "未知类型"
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    bookId: null,
    bookName: null,
    bookAuthor: null,
    bookImage: null,
    typeId: null
  }
  proxy.resetForm("bookRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.bookId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加图书信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _bookId = row.bookId || ids.value
  getBook(_bookId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改图书信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bookRef"].validate(valid => {
    if (valid) {
      if (form.value.bookId != null) {
        updateBook(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addBook(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _bookIds = row.bookId || ids.value
  proxy.$modal.confirm('是否确认删除图书信息编号为"' + _bookIds + '"的数据项？').then(function() {
    return delBook(_bookIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('book/book/export', {
    ...queryParams.value
  }, `book_${new Date().getTime()}.xlsx`)
}

// 初始化加载数据
getList()
getTypeList()
</script>