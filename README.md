docker-compose -f docker-compose.yaml up -d --build
docker build -t alexandreqrz/rango:latest . &&
docker push alexandreqrz/rango:latest