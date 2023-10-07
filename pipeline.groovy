pipeline {
    agent any
    stages {
        stage('pull') { 
            steps {
               echo "successfull pull from private repo"
              git branch: 'master', url: 'https://gitlab.com/hanumane96/studentapp-ui.git'
            }
        }
        stage('build') { 
            steps {
                echo "build by maven " 
                sh '''
                /opt/apache-maven-3.9.4/bin/mvn clean package
                   '''
            }
        }
        stage('test') {
            steps {
                echo "test is getting successfull by sonarqube"
                withSonarQubeEnv(installationName: 'sonar' , credentialsId: 'sonar') {
                sh '/opt/apache-maven-3.9.4/bin/mvn sonar:sonar -Dsonar.projectKey=studentapp'           
                }
             }
        }
        stage('deploy') {
            steps {
                echo "this is deployment stage"
                deploy adapters: [tomcat8(credentialsId: 'tomcat', path: '', url: 'http://18.170.66.102:8080')], contextPath: '/', war: '**/*.war'
            }
        }

        }
}  