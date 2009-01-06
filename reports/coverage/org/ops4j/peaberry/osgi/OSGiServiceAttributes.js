var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":602,"sl":31,"methods":[{"sl":36,"el":38,"sc":3},{"sl":40,"el":43,"sc":3},{"sl":48,"el":87,"sc":3},{"sl":53,"el":77,"sc":9},{"sl":62,"el":64,"sc":13},{"sl":66,"el":71,"sc":13},{"sl":73,"el":75,"sc":13},{"sl":79,"el":82,"sc":9}],"el":88,"name":"OSGiServiceAttributes"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":36},{"sl":40}],"name":"testServiceExports","statements":[{"sl":37},{"sl":42}],"pass":true},"test_27":{"methods":[{"sl":36}],"name":"testServiceLookupPerformance","statements":[{"sl":37}],"pass":true},"test_12":{"methods":[{"sl":36}],"name":"testDirectServiceInjection","statements":[{"sl":37}],"pass":true},"test_16":{"methods":[{"sl":36}],"name":"rank","statements":[{"sl":37}],"pass":true},"test_21":{"methods":[{"sl":36}],"name":"testServiceInjection","statements":[{"sl":37}],"pass":true},"test_20":{"methods":[{"sl":36}],"name":"testDecoratedServiceInjection","statements":[{"sl":37}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [20, 21, 16, 12, 1, 27], [20, 21, 16, 12, 1, 27], [], [], [1], [], [1], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
