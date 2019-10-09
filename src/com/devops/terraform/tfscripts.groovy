package com.devops.terraform

void setValue(String action)
{
   	println "function startedi with set value"
	this.tfaction = action
}



def terraform()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF action. Please wait...\u001B[0m "
      dir ('erv/'){
	sh label: '', script: '''echo "action is $tfaction"
        terraform "${tfaction}"
'''    
	}
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m." + action
    }
    catch (Exception error) {
      println "\u001B[41m [ERROR] failed to initialize terraform."
      throw error
    }
  }
}
