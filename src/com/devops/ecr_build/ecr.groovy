package com.devops.ecr_build

def push()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Copying EDA&Data processing scripts. Please wait...\u001B[0m "
        dir ('erv/src/Deployment/')
	echo '$image_name'
	sh '$(aws ecr get-login)'
	sh 'bash build_and_push.sh churn v$BUILD_ID'
      println "\u001B[32m [INFO] \u001B[0m: Copying EDA&Data processing scripts is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to copying EDA&DATA."
      throw error
    }
  }


}

