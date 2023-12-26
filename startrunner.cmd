docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" -v C:\\Users\\diego\\Documents\\GitHub\\dagserver-selenium\\statics:/statics selenium/standalone-chrome:latest
docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest
docker run -d --hostname my-rabbit --name dockerrabbit -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=rabbituser -e RABBITMQ_DEFAULT_PASS=telepassword.2022 rabbitmq:3-management 
