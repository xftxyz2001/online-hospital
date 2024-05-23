# 网上医院

## 运行
```bash
sh run.sh
```

访问
- 医生端 ~/doctor/
- 管理端 ~/admin/
- 用户端 修改 [app.js](./miniapp-wx/app.js) 中的 `ONLINE_HOSPITAL_GATEWAY_HOST` 为 部署服务器的域名/ip

### 环境
- JDK 21+
- Node.js 20+
- Docker 26+
- Docker Compose 1.29+

> 便捷版（自动安装所需的依赖（无需事先clone项目））:   
> ```
> curl -qO- https://raw.githubusercontent.com/xftxyz2001/online-hospital/main/setup.sh | bash
> ```

### 运行截图
[见运行截图](./screenshot/)


## 开发

### 项目结构
- 后端【Spring】 [server](./server/)
  - 基础模块 [base](./server/online-hospital-base/)
  - 网关 [gateway](./server/online-hospital-gateway/)
  - 用户-api [user](./server/online-hospital-user/)
  - 医院-api [hospital](./server/online-hospital-hospital/)
  - 预约挂号-api [registration](./server/online-hospital-registration/)
  - 在线问诊-api [inquiry](./server/online-hospital-inquiry/)
  - 系统-api [system](./server/online-hospital-system/)

- 医生端【Vite】 [doctor](./site-doctor/)
- 管理端【Vite】 [admin](./site-admin/)
- 用户端【微信小程序】 [miniapp-wx](./miniapp-wx/)

### 环境
- JDK 21.0.3
- Node.js 20.12.0
- Nacos 2.3.2
- MySQL 8.2.0

### hosts
```
127.0.0.1 nacos
127.0.0.1 mysql
127.0.0.1 gateway
```
