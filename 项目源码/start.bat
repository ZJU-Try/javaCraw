#  @��ʾ�������������һ�������ֻӡ��ǰ��
#  echo off��ʾ������������������ʾ���ֻ��ʾ�����������echo on��
@echo off

# ����classpath��������Ŀ������jar��
set classpath=.;%cd%\cn\qlq\craw\lib\*

# ����Դ����
javac .\cn\qlq\craw\JsoupCrawJWXT\*.java

# ִ�������򣬿�ʼ�������� 
java cn.qlq.craw.JsoupCrawJWXT.MainClass

# ִ�����֮�󲻹ػ����ڣ��û����������֮��رմ���
pause