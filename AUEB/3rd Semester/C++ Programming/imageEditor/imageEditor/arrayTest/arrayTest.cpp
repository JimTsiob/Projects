#include <iostream>
#include <vector>
#include "array2d.h"

using namespace std;


int main() {
	unsigned int width = 2;
	unsigned int height = 2;
	vector<float> vec{ 1.1f,2.2f,3.3f };
	vector<float> vec2{ 4.4f,5.5f,6.6f };
	const float* data = vec.data();
	const float* const& data2 = vec2.data();
	math::Array2D<float> item = math::Array2D<float>(width,height,data); // dhmiourgia disdiastatou pinaka 2x2.
	math::Array2D<float> copied(item); // Antigrafo tou item (me to Copy Constructor).
	math::Array2D<float> sure = item; // 2os elegxos tou copy constructor.
	copied.setData(data2); // elegxos tou setData, bazoume data apo diaforetiko vector gia na doume an tha exei diaforetika stoixeia.
	float num2 = copied.operator()(0,0); // test tou overloaded () operator.
	math::Array2D<float>newCopy = math::Array2D<float>(); // edw elegxetai oti to default constructor douleyei swsta.
	newCopy.operator=(item); // Antigrafo tou item (me Copy assignment operator).
	float num = item.operator()(0,0); 
	float num3 = newCopy.operator()(0,0);
	cout << "Width of newCopy is: " << newCopy.getWidth() << endl;
	cout << "item first element is: " << num << endl;
	cout << "copied first element is: "<< num2 << endl;
	cout <<"newCopy element at (0,0): "<< num3 << endl;
	cout << "Data of item is: " << item.getRawDataPtr() << endl;
	cout << "Data of copied after set is: " << copied.getRawDataPtr() << endl;
	cout << "Width of newCopy is: " << newCopy.getWidth() << endl;
	cout << "Width of item is: " << item.getWidth() << endl;
	cout << "Height of item is: " << item.getHeight() << endl;
	cout << "Width of copied is: " << copied.getWidth() << endl;
	item.~Array2D(); // katastrofh disdiastatou pinaka.
	copied.~Array2D(); // katastrofh antigrafwn.
	newCopy.~Array2D();
	system("PAUSE");
	return 0;
}

