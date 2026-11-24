SRC_DIR     := src
BUILD_DIR   := build
MAIN_CLASS  := Main
JAR         := Memento.jar

JAVAC       := javac
JAVA        := java
JAR_TOOL    := jar
JAVAC_FLAGS := -d $(BUILD_DIR) -sourcepath $(SRC_DIR) -encoding UTF-8

SOURCES         := $(shell find $(SRC_DIR) -name '*.java')
MAIN_CLASS_FILE := $(BUILD_DIR)/$(MAIN_CLASS).class

.PHONY: all compile run clean rebuild

all: $(JAR)

compile: $(MAIN_CLASS_FILE)

$(MAIN_CLASS_FILE): $(SOURCES)
	rm -rf $(BUILD_DIR)
	mkdir -p $(BUILD_DIR)
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

$(JAR): $(MAIN_CLASS_FILE)
	$(JAR_TOOL) cfe $@ $(MAIN_CLASS) -C $(BUILD_DIR) .

run: $(JAR)
	$(JAVA) -jar $(JAR)

clean:
	rm -rf $(BUILD_DIR) $(JAR)

rebuild: clean all
