var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = { "classes" : [
    {"id" : 148, "sl" : 32, "el" : 78, "name" : "ServiceProxyFactory",
    "methods" : [
              {"sl" : 35, "el" : 35, "sc" : 3},  {"sl" : 45, "el" : 59, "sc" : 3},  {"sl" : 50, "el" : 55, "sc" : 7},  {"sl" : 69, "el" : 77, "sc" : 3},  {"sl" : 73, "el" : 75, "sc" : 7}  ]}
    
 ]
};

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
		"test_2" : {
					  "name" : "staticUnaryService",
					  "pass" : true ,
					  "methods" : [{"sl": 50 },],
					  "statements" : [{"sl": 54 },]
					  },
		"test_4" : {
					  "name" : "unleasedUnaryService",
					  "pass" : true ,
					  "methods" : [{"sl": 50 },],
					  "statements" : [{"sl": 54 },]
					  },
		"test_0" : {
					  "name" : "leasedUnaryService",
					  "pass" : true ,
					  "methods" : [{"sl": 50 },],
					  "statements" : [{"sl": 54 },]
					  },
		"test_1" : {
					  "name" : "staticMultiService",
					  "pass" : true ,
					  "methods" : [{"sl": 73 },],
					  "statements" : [{"sl": 74 },]
					  },
		"test_3" : {
					  "name" : "leasedMultiService",
					  "pass" : true ,
					  "methods" : [{"sl": 73 },],
					  "statements" : [{"sl": 74 },]
					  }
 };

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [  [],   [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 2 , 4 , 0   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 2 , 4 , 0   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 1 , 3   ] ,
  [ 1 , 3   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] 
];
