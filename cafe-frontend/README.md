## Cafe Management Frontend module

This project directory contains all frontend-related files and directories.
It's written in React and uses Material UI as a frontend ui library.

NPM is used here as a Package Manager and build tool.

1. `npm install` command can be used to install all external dependencies locally
2. `npm run build` command can be used to make a production build of frontend

The Dockerfile for this service is based on Nginx 1.17.9-alpine. It will serve as a proxy for this SPA, redirecting all the incoming traffic to index.html. 
