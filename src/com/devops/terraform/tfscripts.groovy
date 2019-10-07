package com.devops.terraform

def tfInit()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF initialization. Please wait...\u001B[0m "
      dir ('erv/'){
        sh 'terraform --version'
        sh 'terraform init'
            }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to initialize terraform."
      throw error
    }
  }
}
def tfPaln()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF planing. Please wait...\u001B[0m "
      dir ('erv/'){
        sh 'terraform get --update'
        sh 'terraform plan'
            }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to plan terraform."
      throw error
    }
  }
def tfapply()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF apply. Please wait...\u001B[0m "
      dir ('erv/'){
        sh 'terraform apply --auto-approve'
            }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to apply terraform."
      throw error
    }
  }

}
