rem @��ʾ�������������һ�������ֻӡ��ǰ��
rem  echo off��ʾ������������������ʾ���ֻ��ʾ�����������echo on��
@echo off

rem ����classpath��������Ŀ������jar��
set classpath=.;%cd%\cn\qlq\craw\lib\*

rem ����Դ����
javac .\cn\qlq\craw\JsoupCrawJWXT\*.java

rem ִ�������򣬿�ʼ�������� 
java cn.qlq.craw.JsoupCrawJWXT.MainClass

rem ִ�����֮�󲻹ػ����ڣ��û����������֮��رմ���
pause
