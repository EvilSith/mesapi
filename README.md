# MesAPI
### Simple send messages and documents telegram.
<br/>

## Build
`docker build -t mes-api .`
## Run
`docker run --rm -p 8080:8080 -e DEF_CHAT_ID=XXXXXXX -e TOKEN=YYYYYYYY:ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ mes-api`