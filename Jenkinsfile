pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'MAVEN_HOME'
    }
    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'git-token', url: 'https://github.com/Ibtissam-Elamine/GestionBibliotheque.git'

            }
        }
        stage('Build') {
            steps {
                sh '${MAVEN_HOME}/bin/mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh '${MAVEN_HOME}/bin/mvn test'
            }
        }
        stage('Quality Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh '${MAVEN_HOME}/bin/mvn sonar:sonar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Déploiement simulé réussi'
            }
        }
    }

}
