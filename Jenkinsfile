pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t mock-app:latest .'
            }
        }

        stage('Run Application') {
            steps {
                sh '''
                    docker rm -f mock-app || true
                    docker run -d \
                        --name mock-app \
                        -p 8082:8080 \
                        mock-app:latest
                '''
            }
        }
    }
}