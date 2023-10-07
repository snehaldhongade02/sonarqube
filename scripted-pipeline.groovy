node {  
    stage('Pull') { 
        echo 'Hello World'
    }
    stage('Build') { 
         echo 'Build Success'
    }
    stage('Test') { 
         echo 'Congrats! Test is done'
    }
    stage('Deploy') { 
         echo 'Yahoo! Deploy worked'
    }
}
