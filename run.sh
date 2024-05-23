#!/bin/sh
# 0 清理
docker rm -f $(docker ps -aq)

docker rmi -f xftxyz/onlinehospital-gateway:0.0.1
docker rmi -f xftxyz/onlinehospital-user:0.0.1
docker rmi -f xftxyz/onlinehospital-hospital:0.0.1
docker rmi -f xftxyz/onlinehospital-registration:0.0.1
docker rmi -f xftxyz/onlinehospital-inquiry:0.0.1
docker rmi -f xftxyz/onlinehospital-system:0.0.1
docker rmi -f xftxyz/onlinehospital-doctor:0.0.1
docker rmi -f xftxyz/onlinehospital-admin:0.0.1

# 1 构建
## 后端
cd server
chmod +x mvnw
./mvnw clean package -DskipTests

## 医生端
cd ../site-doctor
npm install
npm run build

## 后台管理
cd ../site-admin
npm install
npm run build

## 构建docker镜像
cd ..
docker-compose build

# 2 初始化
# user
if [ ! -d ./config/user ]; then
    mkdir -p ./config/user
    echo "下面是微信相关配置，参考（开发->开发管理->开发设置->开发者ID）: "
    echo "https://mp.weixin.qq.com/wxamp/devprofile/get_profile/lang=zh_CN"

    read -p "请输入微信小程序的AppID: " appid
    read -p "请输入微信小程序的AppSecret: " appsecret

    cat <<EOF >./config/user/application.yml
nwu:
  wechat:
    appid: $appid
    secret: $appsecret
EOF
fi

# system
if [ ! -d ./config/system ]; then
    mkdir -p ./config/system
    echo "下面是阿里云相关配置，参考: "
    echo "https://ram.console.aliyun.com/manage/ak"

    read -p "请输入阿里云的AccessKey ID: " accesskeyid
    read -p "请输入阿里云的AccessKey Secret: " accesskeysecret

    cat <<EOF >./config/system/application.yml
aliyun:
  oss:
    access-key-id: $accesskeyid
    access-key-secret: $accesskeysecret
    endpoint: https://oss-cn-hangzhou.aliyuncs.com
    bucket-name: online-hospital-nwu
EOF
fi

echo "微服务已配置完成！"

# 3 启动
docker-compose up -d

echo "服务已启动，需放行端口：80"
echo "~/doctor/  医生端"
echo "~/admin/   后台管理"
