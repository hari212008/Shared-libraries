
//Define Code checkout
def codeCheckout()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      this.branch_name = "$branch"
      cleanWs()
      println "\u001B[32m [INFO] Starting the code checkout. Please wait...\u001B[0m "
      checkout (
        changelog: false,
        poll: false,
        scm :[
        $class: 'GitSCM',
        branches: [[name: "${branch_name}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[url: 'https://github.com/hari212008/Erv.git']]
        ]
        )
      println "\u001B[32m [INFO] \u001B[0m: Code checkout from \u001B[32m${branch_name}\u001B[0m is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to Checkout the tag $branch_name."
      throw error
    }
  }
}




//Define EDA&DATA copy
def copying()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Copying EDA&Data processing scripts. Please wait...\u001B[0m "
        sh 'sudo cp -R erv/ /jenkins/terraform/modules/usecase-setup/source'
      println "\u001B[32m [INFO] \u001B[0m: Copying EDA&Data processing scripts is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to EDA&Data copy Failed."
      throw error
    }
  }
}



//Def  terraform Execution

def terraformCheckout()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      this.branch_name = "$branch"
      cleanWs()
      println "\u001B[32m [INFO]Starting Terraform code checkout. Please wait...\u001B[0m "
      checkout (
        changelog: false,
        poll: false,
        scm :[
        $class: 'GitSCM',
        branches: [[name: "${branch_name}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[url: 'https://github.com/hari212008/terrafrm.git']]
        ]
        )
      println "\u001B[32m [INFO] \u001B[0m: Code checkout from \u001B[32m${branch_name}\u001B[0m is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to Checkout the tag $branch_name."
      throw error
    }
  }
}

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

def tfplan()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF plan. Please wait...\u001B[0m "
     dir ('erv/') {
      sh 'terraform get --update'
      sh 'terraform plan '
    }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to plan terraform."
      throw error
    }
  }
}


def tfdestroy()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "\u001B[32m [INFO] Starting TF apply. Please wait...\u001B[0m "
     dir ('erv/') {
      sh 'terraform destroy -auto'
    }
      println "\u001B[32m [INFO] \u001B[0m: TF initialization is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to apply terraform."
      throw error
    }
  }
}
