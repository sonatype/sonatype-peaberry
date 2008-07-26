var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = { "classes" : [
    {"id" : 0, "sl" : 32, "el" : 118, "name" : "ServiceProxyFactory",
    "methods" : [
              {"sl" : 35, "el" : 35, "sc" : 3},  {"sl" : 37, "el" : 62, "sc" : 3},  {"sl" : 41, "el" : 60, "sc" : 7},  {"sl" : 47, "el" : 49, "sc" : 11},  {"sl" : 51, "el" : 54, "sc" : 11},  {"sl" : 56, "el" : 58, "sc" : 11},  {"sl" : 64, "el" : 113, "sc" : 3},  {"sl" : 82, "el" : 94, "sc" : 7},  {"sl" : 96, "el" : 108, "sc" : 7},  {"sl" : 115, "el" : 117, "sc" : 3}  ]}
    
 ]
};

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
		"test_6" : {
					  "name" : "testContention",
					  "pass" : true,
					  "methods" : [{"sl": 82 },{"sl": 96 },],
					  "statements" : [{"sl": 83 },{"sl": 84 },{"sl": 86 },{"sl": 87 },{"sl": 89 },{"sl": 93 },{"sl": 97 },{"sl": 98 },{"sl": 100 },{"sl": 101 },{"sl": 104 },{"sl": 105 },]
					  },
		"test_13" : {
					  "name" : "run",
					  "pass" : true,
					  "methods" : [{"sl": 82 },{"sl": 96 },],
					  "statements" : [{"sl": 83 },{"sl": 84 },{"sl": 89 },{"sl": 93 },{"sl": 97 },{"sl": 98 },{"sl": 100 },{"sl": 101 },{"sl": 104 },{"sl": 105 },]
					  },
		"test_9" : {
					  "name" : "stickyService",
					  "pass" : true,
					  "methods" : [{"sl": 82 },],
					  "statements" : [{"sl": 83 },{"sl": 84 },{"sl": 86 },{"sl": 87 },{"sl": 89 },{"sl": 93 },]
					  },
		"test_1" : {
					  "name" : "checkInjection",
					  "pass" : true,
					  "methods" : [{"sl": 41 },{"sl": 47 },{"sl": 51 },{"sl": 82 },{"sl": 96 },{"sl": 115 },],
					  "statements" : [{"sl": 42 },{"sl": 48 },{"sl": 53 },{"sl": 83 },{"sl": 84 },{"sl": 86 },{"sl": 87 },{"sl": 89 },{"sl": 93 },{"sl": 97 },{"sl": 98 },{"sl": 100 },{"sl": 101 },{"sl": 104 },{"sl": 105 },{"sl": 116 },]
					  },
		"test_14" : {
					  "name" : "checkRanking",
					  "pass" : true,
					  "methods" : [{"sl": 41 },{"sl": 47 },{"sl": 51 },{"sl": 56 },{"sl": 115 },],
					  "statements" : [{"sl": 42 },{"sl": 48 },{"sl": 53 },{"sl": 57 },{"sl": 116 },]
					  },
		"test_8" : {
					  "name" : "testWiring",
					  "pass" : true,
					  "methods" : [{"sl": 82 },{"sl": 96 },],
					  "statements" : [{"sl": 83 },{"sl": 84 },{"sl": 86 },{"sl": 87 },{"sl": 89 },{"sl": 93 },{"sl": 97 },{"sl": 98 },{"sl": 100 },{"sl": 101 },{"sl": 104 },{"sl": 105 },]
					  },
		"test_0" : {
					  "name" : "checkScoping",
					  "pass" : true,
					  "methods" : [{"sl": 82 },{"sl": 96 },],
					  "statements" : [{"sl": 83 },{"sl": 84 },{"sl": 86 },{"sl": 87 },{"sl": 89 },{"sl": 91 },{"sl": 93 },{"sl": 97 },{"sl": 98 },{"sl": 100 },{"sl": 101 },{"sl": 104 },{"sl": 105 },]
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
  [ 14 , 1   ] ,
  [ 14 , 1   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 14 , 1   ] ,
  [ 14 , 1   ] ,
  [  ] ,
  [  ] ,
  [ 14 , 1   ] ,
  [  ] ,
  [ 14 , 1   ] ,
  [  ] ,
  [  ] ,
  [ 14   ] ,
  [ 14   ] ,
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
  [ 0 , 13 , 8 , 6 , 1 , 9   ] ,
  [ 0 , 13 , 8 , 6 , 1 , 9   ] ,
  [ 0 , 13 , 8 , 6 , 1 , 9   ] ,
  [  ] ,
  [ 0 , 8 , 6 , 1 , 9   ] ,
  [ 0 , 8 , 6 , 1 , 9   ] ,
  [  ] ,
  [ 0 , 13 , 8 , 6 , 1 , 9   ] ,
  [  ] ,
  [ 0   ] ,
  [  ] ,
  [ 0 , 13 , 8 , 6 , 1 , 9   ] ,
  [  ] ,
  [  ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [  ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [  ] ,
  [  ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [ 0 , 13 , 8 , 6 , 1   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 14 , 1   ] ,
  [ 14 , 1   ] ,
  [  ] ,
  [  ] 
];
