pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "url-checker-app"
        DOCKERHUB_REPO = "shreeyali/url-checker-app"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Shreeyalisingh/url-status-checker'
            }
        }

        stage('Build with Maven') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Run Unit Tests') {
            steps {
                bat 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Run Docker Container') {
            steps {
                bat 'docker run --rm %IMAGE_NAME%'
            }
        }

        stage('Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                }
            }
        }

        stage('Tag & Push to DockerHub') {
            steps {
                bat 'docker tag %IMAGE_NAME% %DOCKERHUB_REPO%'
                bat 'docker push %DOCKERHUB_REPO%'
            }
        }
    }
}
