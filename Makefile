
JAVA_HOME = /opt/jdk23

BIN = $(JAVA_HOME)/bin/

default: cf.jar

cf.jar: src/Main.java
	rm -fr bin; mkdir bin
	$(BIN)javac -d bin -sourcepath src --enable-preview --release 23 $<
	$(BIN)jar -c -f $@ -e $(basename $(<F)) -C bin .

example/%: cf.jar
	$(eval E := $(basename $(@F)))
	rm -fr bin; mkdir bin
	$(BIN)javac -d bin $@
	mkdir -p $(E)
	find bin -name "$(E).class" -exec mv -v {} $(E)/a.class ';'
	$(BIN)javap -v -l -p -c -s -sysinfo -constants $(E)/a.class > $(E)/a.javap
	$(BIN)java --enable-preview -jar $< disasm $(E)/a.class > $(E)/a.disasm
	$(BIN)java --enable-preview -jar $< tokens $(E)/a.disasm > $(E)/a.tokens

clean:
	rm -fr bin cf.jar       
	find . -name 'a.class' -printf %h | xargs --verbose rm -fr

