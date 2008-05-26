var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = { "classes" : [
    {"id" : 670, "sl" : 34, "el" : 112, "name" : "OSGiServiceTester",
    "methods" : [
             {"sl" : 46, "el" : 65, "sc" : 3},  {"sl" : 52, "el" : 58, "sc" : 7},  {"sl" : 67, "el" : 69, "sc" : 3},  {"sl" : 71, "el" : 76, "sc" : 3},  {"sl" : 78, "el" : 81, "sc" : 3},  {"sl" : 83, "el" : 88, "sc" : 3},  {"sl" : 90, "el" : 105, "sc" : 3},  {"sl" : 107, "el" : 111, "sc" : 3}  ]}
    ,
    {"id" : 670, "sl" : 36, "el" : 38, "name" : "OSGiServiceTester.SimpleService",
    "methods" : [
              ]}
    
 ]
};

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {
		"test_15" : {
					  "name" : "unleasedUnaryService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 83 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 84 },{"sl": 85 },]
					  },
		"test_13" : {
					  "name" : "staticMultiService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 90 },{"sl": 107 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 57 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 73 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 92 },{"sl": 93 },{"sl": 94 },{"sl": 95 },{"sl": 96 },{"sl": 98 },{"sl": 102 },{"sl": 104 },{"sl": 108 },{"sl": 109 },]
					  },
		"test_18" : {
					  "name" : "leasedUnaryService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 83 },{"sl": 107 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 57 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 73 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 84 },{"sl": 85 },{"sl": 108 },{"sl": 109 },]
					  },
		"test_12" : {
					  "name" : "staticUnaryService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 83 },{"sl": 107 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 57 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 73 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 84 },{"sl": 85 },{"sl": 108 },{"sl": 109 },]
					  },
		"test_14" : {
					  "name" : "testUnaryService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 71 },{"sl": 78 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 72 },{"sl": 75 },{"sl": 79 },{"sl": 80 },]
					  },
		"test_0" : {
					  "name" : "testMultiService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 90 },{"sl": 107 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 57 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 73 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 92 },{"sl": 93 },{"sl": 94 },{"sl": 95 },{"sl": 96 },{"sl": 98 },{"sl": 102 },{"sl": 104 },{"sl": 108 },{"sl": 109 },]
					  },
		"test_9" : {
					  "name" : "checkInjection",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 71 },{"sl": 78 },{"sl": 83 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 72 },{"sl": 73 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 84 },{"sl": 85 },]
					  },
		"test_6" : {
					  "name" : "leasedMultiService",
					  "pass" : true,
					  "methods" : [{"sl": 46 },{"sl": 52 },{"sl": 67 },{"sl": 71 },{"sl": 78 },{"sl": 90 },{"sl": 107 },],
					  "statements" : [{"sl": 47 },{"sl": 48 },{"sl": 49 },{"sl": 51 },{"sl": 53 },{"sl": 54 },{"sl": 57 },{"sl": 61 },{"sl": 63 },{"sl": 64 },{"sl": 68 },{"sl": 72 },{"sl": 75 },{"sl": 79 },{"sl": 80 },{"sl": 92 },{"sl": 93 },{"sl": 94 },{"sl": 95 },{"sl": 96 },{"sl": 98 },{"sl": 102 },{"sl": 104 },{"sl": 108 },{"sl": 109 },]
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
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 13 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 15 , 13 , 0 , 18   ] ,
  [ 12 , 6 , 15 , 13 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [ 12 , 6 , 14 , 15 , 13 , 9 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 15 , 9 , 18   ] ,
  [ 12 , 15 , 9 , 18   ] ,
  [ 12 , 15 , 9 , 18   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 6 , 13 , 0   ] ,
  [  ] ,
  [ 6 , 13 , 0   ] ,
  [ 6 , 13 , 0   ] ,
  [ 6 , 13 , 0   ] ,
  [ 6 , 13 , 0   ] ,
  [ 6 , 13 , 0   ] ,
  [  ] ,
  [ 6 , 13 , 0   ] ,
  [  ] ,
  [  ] ,
  [  ] ,
  [ 6 , 13 , 0   ] ,
  [  ] ,
  [ 6 , 13 , 0   ] ,
  [  ] ,
  [  ] ,
  [ 12 , 6 , 13 , 0 , 18   ] ,
  [ 12 , 6 , 13 , 0 , 18   ] ,
  [ 12 , 6 , 13 , 0 , 18   ] ,
  [  ] ,
  [  ] ,
  [  ] 
];
