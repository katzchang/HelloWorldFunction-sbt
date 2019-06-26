

#SBT:=docker run -p 8080:8080 -v ~/.ivy2:/root/.ivy2  -v ~/.sbt:/root/.sbt -v `pwd`:/workspace -w /workspace -it --rm hseeberger/scala-sbt sbt
SBT=./sbt

.PHONY: jetty-start sbt-shell

test-continuous: sbt
	$(SBT) ~test

sbt-shell: sbt
	$(SBT)

sbt-assembly: sbt
	$(SBT) assembly

sbt:
	curl -Ls https://git.io/sbt > $@ && chmod 0755 $@


## SAM
project-name:=HelloWorldFunction
build-target:=.aws-sam/build
package-name:=hello-sam-assembly-0.1.0-SNAPSHOT.jar

.PHONY: sam-build
sam-build: $(build-target)/template.yaml $(build-target)/$(project-name)/lib/$(package-name)

sam-start-api: sam-build
	sam local start-api

$(build-target):
	mkdir -p $@

$(build-target)/template.yaml: template.yaml $(build-target)
	cp -f $< $@

$(build-target)/$(project-name)/lib:
	mkdir -p $@

$(build-target)/$(project-name)/lib/$(package-name): target/scala-2.12/hello-sam-assembly-0.1.0-SNAPSHOT.jar $(build-target)/$(project-name)/lib
	cp -f $< $@
