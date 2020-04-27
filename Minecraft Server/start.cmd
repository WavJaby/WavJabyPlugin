@echo off

REM UTF-8編碼
REM chcp 65001

REM 原本的編碼
chcp 950

echo ###########Server Start###########

REM 得到資料夾位置
for %%i in ("%~dp0.") do (
	set "folder=%%~fi"
)
echo server Folder: %folder%

for %%i in (spigot*.jar) do (
	set "serverjar=%%i"
)
echo Server jar: %serverjar%


REM 開啟伺服器
java -Xms1G -Xmx4G -jar %serverjar% nogui


