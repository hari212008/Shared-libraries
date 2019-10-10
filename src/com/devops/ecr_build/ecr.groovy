package com.devops.ecr_build

def push()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
        dir ('erv/src/Deployment/')
	sh 'ls -lrth'
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to copying EDA&DATA."
      throw error
    }
  }


}

