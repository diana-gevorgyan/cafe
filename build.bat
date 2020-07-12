call cd ./cafe-frontend
call npm install
call npm run build
call docker build -t cafe-frontend:only .
call cd ../cafe-backend
call mvn clean package
call docker build -t cafe-backend:only .
call cd ..
