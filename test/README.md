# Test environment

## Install

```shell
# Download newman
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
nvm install --lts
npm i newman --save-dev
# Download and up mock and app server
docker-compose up
```

## Run tests

```shell
node_modules/.bin/newman run cases/MesAPI.postman_collection.json
```

## Documentation

- [mock-server documentation](https://www.mock-server.com/)
- [newman cli github repo](https://github.com/postmanlabs/newman/)
