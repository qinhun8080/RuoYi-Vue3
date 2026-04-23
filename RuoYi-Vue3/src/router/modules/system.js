import Layout from '@/layout'

const systemRouter = {
    path: '/system',
    component: Layout,
    redirect: '/system/user',
    alwaysShow: true,
    name: 'System',
    meta: {
        title: '系统管理',
        icon: 'system'
    },
    children: [
        {
            path: 'user',
            component: () => import('@/views/system/user/index'),
            name: 'User',
            meta: { title: '用户管理', icon: 'user', activeMenu: '/system/user' }
        },
        {
            path: 'role',
            component: () => import('@/views/system/role/index'),
            name: 'Role',
            meta: { title: '角色管理', icon: 'role', activeMenu: '/system/role' }
        },
        {
            path: 'menu',
            component: () => import('@/views/system/menu/index'),
            name: 'Menu',
            meta: { title: '菜单管理', icon: 'menu', activeMenu: '/system/menu' }
        },
        {
            path: 'dept',
            component: () => import('@/views/system/dept/index'),
            name: 'Dept',
            meta: { title: '部门管理', icon: 'dept', activeMenu: '/system/dept' }
        },
        {
            path: 'post',
            component: () => import('@/views/system/post/index'),
            name: 'Post',
            meta: { title: '岗位管理', icon: 'post', activeMenu: '/system/post' }
        },
        {
            path: 'dict',
            component: () => import('@/views/system/dict/index'),
            name: 'Dict',
            meta: { title: '字典管理', icon: 'dict', activeMenu: '/system/dict' }
        },
        {
            path: 'config',
            component: () => import('@/views/system/config/index'),
            name: 'Config',
            meta: { title: '参数设置', icon: 'config', activeMenu: '/system/config' }
        },
        {
            path: 'notice',
            component: () => import('@/views/system/notice/index'),
            name: 'Notice',
            meta: { title: '通知公告', icon: 'notice', activeMenu: '/system/notice' }
        }
    ]
}

export default systemRouter