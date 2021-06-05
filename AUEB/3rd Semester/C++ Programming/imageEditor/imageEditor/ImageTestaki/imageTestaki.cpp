#include <iostream>
#include "array2d.h"
#include "array2d.hpp"
#include "Filter.h"
#include "Vec3.h"
#include "ppm.h"
using namespace std;


int main() {
	Image test = Image();
	string path = "../../../ExampleImages/Image02.ppm";
	const string& ref = path;
	const string& form = "ppm";
	test.load(ref, form);
	Color num = test.operator()(0,0);
	Color num2 = test.operator()(0,1);
	Color a = Color(1,0.8,0.3);
	Color c = Color(0.1,0.1,0.3);
	float g = 0.7f;
	float k = 2.0f;
	//Image lul = Image();
	/*num = num.operator*(a);
	num = num.operator+(c);
	lul.buffer.push_back(num);
	cout << lul.buffer[0].x << " " << lul.buffer[0].y << " " << lul.buffer[0].z << endl;*/
	//cout << a.x << " " << a.y << " " << a.z << endl;
	//cout << c.x << " " << c.y << " " << c.z << endl;
	FilterLinear filtro = FilterLinear(a,c); // test tou FilterLinear.
	FilterGamma Gfiltro = FilterGamma(g);
	FilterGamma reverse = FilterGamma(k);
	//test = reverse.operator<<(test);
	test = Gfiltro.operator<<(test);
	test = filtro.operator<<(test);
	cout << "test elements:  "<< test.buffer[0].x << " " << test.buffer[0].y << " " << test.buffer[0].z << endl;
	test.save(ref, form);
	//cout << num.x << " " << num.y <<" "<< num.z << endl;
	//cout << num2.x << " " << num2.y << " " << num2.z << endl;
	system("PAUSE");
	return 0;
}