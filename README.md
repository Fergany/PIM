"# PIM" 
docker pull mysql

docker pull rabbitmq

docker run -d --name myrabbitmq -p 8800:15672 -p 5672:5672 rabbitmq:tag

docker run --name my-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql

docker container start my-mysql

docker container start myrabbitmq
