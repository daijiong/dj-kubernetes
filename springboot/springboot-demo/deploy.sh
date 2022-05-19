#!/bin/sh
cd springboot/springboot-demo
echoRed() { echo $'\e[0;31m'"$1"$'\e[0m'; }
echoGreen() { echo $'\e[0;32m'"$1"$'\e[0m'; }
echoYellow() { echo $'\e[0;33m'"$1"$'\e[0m'; }
SERVER_NAME=springboot-demo
REGISTRY_URL=registry.cn-hangzhou.aliyuncs.com
REPOSITORY=$REGISTRY_URL/djflying/$SERVER_NAME
TAG=kubernetes
echoYellow "=========================>>>>>>>构建本地镜像"
docker build -t $REPOSITORY:$TAG .
echoYellow "=========================>>>>>>>向远程镜像仓库中推送镜像"
docker push $REPOSITORY:$TAG
#镜像id  [grep -w 全量匹配镜像名和TAG] [awk 获取信息行的第三列，即镜像ID]
IID=$(docker images | grep -w "$SERVER_NAME" | grep -w "${TAG}" | awk '{print $3}')
echoYellow "=========================>>>>>>>移除本地镜像，ID=$IID"
docker rmi $IID