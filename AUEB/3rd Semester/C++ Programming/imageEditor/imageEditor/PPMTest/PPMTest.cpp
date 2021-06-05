#include <iostream>
#include "ppm.h"
using namespace std;
							//test file gia thn ReadPPM kai WritePPM.
int main() {
	int width = 0;
	int height = 0;
	string filename = "../../../ExampleImages/Image01.ppm";
	const char* image = filename.c_str();
	float* something = image::ReadPPM(image, &width, &height);
	cout << "Width is: " << width << endl;
	cout << "Height is: " << height << endl;
	string newFile = "../../../ExampleImages/NewImage.ppm";
	const char* image2 = newFile.c_str();
	bool creation = image::WritePPM(something, width, height, image2);
	delete[] something;
	system("PAUSE");
	return 0;
}