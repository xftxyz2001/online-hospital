## 检查git
if ! git --version > /dev/null 2>&1; then
    sudo dnf install git -y
fi
echo "git已安装！"

## 检查jdk
if ! java -version 2>&1 | grep -q "version \"21"; then
    mkdir -p /usr/local/java
    cd /usr/local/java
    wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.tar.gz
    tar -zxvf jdk-21_linux-x64_bin.tar.gz
    jdk_folder=$(ls -d */ | grep 'jdk-21' | sed 's/\/$//')
    echo "export JAVA_HOME=/usr/local/java/$jdk_folder" >> ~/.bashrc
    echo 'export PATH=$PATH:$JAVA_HOME/bin' >> ~/.bashrc
    source ~/.bashrc
fi
echo "jdk已安装！"

## 检查node
if ! node --version 2>&1 | grep -q "v20"; then
    mkdir -p /usr/local/node
    cd /usr/local/node
    wget https://nodejs.org/dist/v20.12.0/node-v20.12.0-linux-x64.tar.xz
    tar -xvf node-v20.12.0-linux-x64.tar.xz
    node_folder=$(ls -d */ | grep 'node-v20' | sed 's/\/$//')
    echo "export NODE_HOME=/usr/local/node/$node_folder" >> ~/.bashrc
    echo 'export PATH=$PATH:$NODE_HOME/bin' >> ~/.bashrc
    source ~/.bashrc
fi
echo "node已安装！"

## 检查docker
if ! docker --version > /dev/null 2>&1; then
    sudo dnf config-manager --add-repo=https://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    sudo dnf -y install dnf-plugin-releasever-adapter --repo alinux3-plus
    sudo dnf -y install docker-ce --nobest
    sudo systemctl start docker
    sudo systemctl enable docker
fi
echo "docker已安装！"

## 检查docker-compose
if ! docker-compose --version > /dev/null 2>&1; then
    sudo pip3 install -U pip setuptools
    sudo pip3 install docker-compose
fi
echo "docker-compose已安装！"

## 准备并进入项目目录
REPOSITORY_NAME="online-hospital"

if [[ $(basename "$PWD") != $REPOSITORY_NAME ]]; then
    if [ -d $REPOSITORY_NAME ]; then
        rm -rf $REPOSITORY_NAME
    fi
    git clone https://github.com/xftxyz2001/$REPOSITORY_NAME.git
    cd $REPOSITORY_NAME
fi
sh run.sh
