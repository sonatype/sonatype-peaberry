var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"id":179,"sl":41,"methods":[{"sl":52,"el":63,"sc":3},{"sl":65,"el":89,"sc":3},{"sl":91,"el":105,"sc":3},{"sl":107,"el":112,"sc":3},{"sl":114,"el":122,"sc":3},{"sl":124,"el":141,"sc":3},{"sl":143,"el":150,"sc":3},{"sl":152,"el":167,"sc":3},{"sl":169,"el":182,"sc":3}],"el":183,"name":"OSGiServiceListener"}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_13":{"methods":[{"sl":152}],"name":"testServiceTypesAndSignatures","statements":[{"sl":156},{"sl":161},{"sl":162}],"pass":true},"test_11":{"methods":[{"sl":91},{"sl":114},{"sl":143},{"sl":152},{"sl":169}],"name":"testServiceInjection","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":116},{"sl":118},{"sl":120},{"sl":145},{"sl":146},{"sl":148},{"sl":156},{"sl":157},{"sl":161},{"sl":162},{"sl":166},{"sl":172},{"sl":175},{"sl":176},{"sl":177}],"pass":true},"test_25":{"methods":[{"sl":91},{"sl":114},{"sl":152},{"sl":169}],"name":"testDecoratedServiceInjection","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":116},{"sl":118},{"sl":120},{"sl":156},{"sl":157},{"sl":161},{"sl":162},{"sl":166},{"sl":172},{"sl":175},{"sl":176},{"sl":177}],"pass":true},"test_21":{"methods":[{"sl":91},{"sl":114},{"sl":124},{"sl":143},{"sl":152},{"sl":169}],"name":"testServiceExports","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":101},{"sl":102},{"sl":103},{"sl":116},{"sl":118},{"sl":120},{"sl":126},{"sl":128},{"sl":130},{"sl":133},{"sl":134},{"sl":135},{"sl":145},{"sl":146},{"sl":148},{"sl":156},{"sl":157},{"sl":161},{"sl":162},{"sl":166},{"sl":172},{"sl":175},{"sl":176},{"sl":177},{"sl":181}],"pass":true},"test_14":{"methods":[{"sl":52},{"sl":65},{"sl":91},{"sl":114},{"sl":143},{"sl":152}],"name":"testDirectServiceInjection","statements":[{"sl":53},{"sl":55},{"sl":58},{"sl":62},{"sl":66},{"sl":69},{"sl":72},{"sl":73},{"sl":76},{"sl":77},{"sl":81},{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":116},{"sl":118},{"sl":120},{"sl":145},{"sl":146},{"sl":148},{"sl":156},{"sl":157},{"sl":161},{"sl":162}],"pass":true},"test_6":{"methods":[{"sl":91},{"sl":114}],"name":"rank","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":116},{"sl":118},{"sl":120}],"pass":true},"test_20":{"methods":[{"sl":91},{"sl":114},{"sl":143}],"name":"run","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":98},{"sl":99},{"sl":100},{"sl":116},{"sl":118},{"sl":120},{"sl":145},{"sl":146},{"sl":148}],"pass":true},"test_8":{"methods":[{"sl":91},{"sl":114}],"name":"rank","statements":[{"sl":92},{"sl":94},{"sl":95},{"sl":96},{"sl":97},{"sl":116},{"sl":118},{"sl":120}],"pass":true},"test_19":{"methods":[{"sl":52},{"sl":65},{"sl":152}],"name":"testServiceLookupPerformance","statements":[{"sl":53},{"sl":55},{"sl":58},{"sl":62},{"sl":66},{"sl":69},{"sl":72},{"sl":73},{"sl":76},{"sl":77},{"sl":81},{"sl":156},{"sl":161},{"sl":162}],"pass":true}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [14, 19], [14, 19], [], [14, 19], [], [], [14, 19], [], [], [], [14, 19], [], [], [14, 19], [14, 19], [], [], [14, 19], [], [], [14, 19], [14, 19], [], [], [14, 19], [14, 19], [], [], [], [14, 19], [], [], [], [], [], [], [], [], [], [8, 14, 25, 21, 11, 20, 6], [8, 14, 25, 21, 11, 20, 6], [], [8, 14, 25, 21, 11, 20, 6], [8, 14, 25, 21, 11, 20, 6], [8, 14, 25, 21, 11, 20, 6], [8, 14, 25, 21, 11, 20, 6], [14, 21, 11, 20], [14, 21, 11, 20], [14, 21, 11, 20], [21], [21], [21], [], [], [], [], [], [], [], [], [], [], [8, 14, 25, 21, 11, 20, 6], [], [8, 14, 25, 21, 11, 20, 6], [], [8, 14, 25, 21, 11, 20, 6], [], [8, 14, 25, 21, 11, 20, 6], [], [], [], [21], [], [21], [], [21], [], [21], [], [], [21], [21], [21], [], [], [], [], [], [], [], [14, 21, 11, 20], [], [14, 21, 11, 20], [14, 21, 11, 20], [], [14, 21, 11, 20], [], [], [], [14, 25, 19, 21, 11, 13], [], [], [], [14, 25, 19, 21, 11, 13], [14, 25, 21, 11], [], [], [], [14, 25, 19, 21, 11, 13], [14, 25, 19, 21, 11, 13], [], [], [], [25, 21, 11], [], [], [25, 21, 11], [], [], [25, 21, 11], [], [], [25, 21, 11], [25, 21, 11], [25, 21, 11], [], [], [], [21], [], []]