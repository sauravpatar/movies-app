properties([pipelineTriggers([githubPush()])])
pipeline {
    agent {
        docker {
            image 'openjdk:17-oracle'
            label 'docker'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    options {
            ansiColor('xterm')
            disableConcurrentBuilds()
            durabilityHint("PERFORMANCE_OPTIMIZED")
    }
    
    stages {
        stage('Test') {
            steps {
                sh 'ls -ltr'
                sh './mvnw test'
            }
        }
        stage('Build') {
            steps {
                echo "This is a build for java"
                sh './mvnw clean package'
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
    }
}
