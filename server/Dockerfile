FROM node:alpine

WORKDIR /usr/src/app
COPY package*.json ./

RUN npm install

ENV HOST 0.0.0.0
EXPOSE 8081
USER node
CMD ["npm","start"]