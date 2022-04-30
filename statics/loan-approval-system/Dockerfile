FROM node:alpine
COPY package.json ./
COPY package-lock.json ./
COPY ./ ./
RUN rm -rf node_modules
RUN npm i
CMD ["npm", "run", "start"]