./gradlew clean build
docker build -t przychodnia .
docker stop przychodnia
docker rm przychodnia
docker run -d -p 8080:8080 --name przychodnia przychodnia