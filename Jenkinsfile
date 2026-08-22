pipeline {
    agent any

    stages {

        stage('Environment Check') {
            steps {
                sh '''
                    echo "===== JAVA ====="
                    which java
                    java -version

                    echo "===== JAVAC ====="
                    which javac
                    javac -version

                    echo "===== MAVEN ====="
                    which mvn
                    mvn -version

                    echo "===== JAVA_HOME ====="
                    echo $JAVA_HOME
                '''
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean test'
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

        stage('Health Check') {
            steps {
                sh '''
                    sleep 10
                    curl -f http://localhost:8082/api/tasks/health
                '''
            }
        }
    }
}