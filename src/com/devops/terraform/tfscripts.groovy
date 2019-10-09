package com.devops.terraform

def terraform()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF initialization. Please wait...\u001B[0m "
      dir ('erv/'){
        sh 'terraform --version'
        sh 'terraform init'
 	sh 'terraform plan'
        sh 'terraform apply -auto-approve'
	    }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
    catch (Exception error) {
      println "\u001B[41m [ERROR] failed to initialize terraform."
      throw error
    }
  }
}
