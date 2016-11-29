@echo off
cd target\
javac -d . ..\source\net\client\socket\*
java -cp . net.client.socket.Application
cd ..