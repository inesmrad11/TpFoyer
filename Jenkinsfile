pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }

    options {
        // Timeout counter starts after agent is allocated
        timeout(time: 30, unit: 'MINUTES')
    }

    environment {
        APP_ENV = "DEV"
    }

    stages {
        stage('Code Checkout') {
            steps {
                git branch: 'services-avances-validation',
                    url: 'https://github.com/inesmrad11/TpFoyer.git'
            }
        }
        stage('Code Build') {
            steps {
                sh 'mvn clean install -Dmaven.test.skip=true'
            }
        }
    }

    post {
        always {
            echo "======always======"
        }
        success {
            echo "=====pipeline executed successfully ====="
        }
        failure {
            echo "======pipeline execution failed======"
        }
    }
}
