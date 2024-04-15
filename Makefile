
JAVA_HOME = /opt/jdk23

BIN = $(JAVA_HOME)/bin/

default: cf.jar

cf.jar: src/Main.java
	rm -fr bin; mkdir bin
	$(BIN)javac -d bin -sourcepath src --enable-preview --release 23 $<
	$(BIN)jar -c -f $@ -e $(basename $(<F)) -C bin .

example/%: cf.jar
	rm -fr bin; mkdir bin
	$(BIN)javac -d bin $@
	mv -v bin/{$@,a}.class
	mv -v bin $@
	$(BIN)javap -v -l -p -c -s -sysinfo -constants $@/a.class > $@/a.javap
	$(BIN)java -jar $< disasm $@/a.class > $@/a.disasm
	$(BIN)java -jar $< tokens $@/a.disasm > $@/a.tokens

clean:
	rm -fr bin cf.jar       
