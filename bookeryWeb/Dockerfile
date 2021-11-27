FROM node:latest as node
WORKDIR /app

ENV NODE_OPTIONS=--openssl-legacy-provider

COPY ["package.json", "package-lock.json", "./"]
RUN npm install
RUN npm install -g @angular/cli@latest

COPY . .

EXPOSE 4200
CMD ["ng","serve","--host", "0.0.0.0", "--port", "4200", "--poll"]