pipeline {
    agent any

    environment {
        WAR_FILE = 'guns-admin\\target\\guns-lite.war'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building project with Maven...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                bat 'mvn -pl guns-admin -am test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Generating JaCoCo coverage report...'
                bat 'mvn -pl guns-admin -am org.jacoco:jacoco-maven-plugin:0.8.8:report'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'guns-admin/target/site/jacoco/**', allowEmptyArchive: true
                }
            }
        }

        stage('Archive Artifact') {
            steps {
                echo 'Archiving WAR file...'
                archiveArtifacts artifacts: 'guns-admin/target/guns-lite.war', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'CI pipeline completed successfully.'
        }
        failure {
            echo 'CI pipeline failed.'
        }
        always {
            echo 'Pipeline finished.'
        }
    }
}