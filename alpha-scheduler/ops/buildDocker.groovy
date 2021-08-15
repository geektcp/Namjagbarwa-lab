dir('xxl-job-admin') {
    runDockerBuild(
            'appName': 'alpha-scheduler',
            'dockerRegistry': 'registry.cn-shenzhen.aliyuncs.com',
            'dockerImage': 'thy/alpha-scheduler'
    )
}
