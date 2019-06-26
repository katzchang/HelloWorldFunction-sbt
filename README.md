# HelloWorldFunction-sbt

[SAM](https://github.com/awslabs/serverless-application-model)'s HelloWorldFunction running with sbt

## How to...

### test api locally

start server:
```bash
make sbt-assembly
make start-api
```

and test:

```bash
curl -v localhost:3000/hello
```
