#include "ppm.h"
#include "array2d.h"
#include "Filter.h"
#include "imageio.h"
#include "Image.h"
#include "vec3.h"
#include <iostream>

using namespace std;



int main(int argc,char* argv[]) {
	float* a = new float[3]; // pinakas gia ta stoixeia tou a.
	float* c = new float[3]; // pinakas gia ta stoixeia tou c.
	Image item = Image(); // edw tha mpei h eikona.
	string path = "../../../ExampleImages/";
	char* file = argv[argc - 1]; // pairnoume to arxeio.
	string checker = file; // elegkths gia to an yparxei to arxeio h oxi.
	size_t pos = checker.find_last_of("."); // vriskei thn teleia.
	if (pos == string::npos) {
		cout << "couldn't find the file. Rewrite the file properly at the end of the command line." << endl;
		system("PAUSE");
		return 1;
	}
	path = path + file; // enwsh gia na ginei px ExampleImages/Image01.ppm.
	cout << "file is: " << path << endl; // dokimh oti ontws einai swsto to path.
	const string& ref = path; // exei to arxiko path gia to load.
	const string& form = "ppm";
	item.load(ref, form); // load ta dedomena ths eikonas.
	size_t slashFinder = path.find_last_of("/");
	string filtered = "Filtered_"; // ayto dhmiourgeitai gia thn enwsh ths lexhs Filtered_ me thn eikona mas.
	path.insert(slashFinder+1, filtered); // edw ginetai h enwsh.
	const string& ref2 = path; // exei to teliko path gia to save.
	cout << "path is: " << path << endl;
	for (int i = 0; i < argc; i++) {
		if (argv[i] == string("-f") && argv[i+1] != string("gamma")) {
			if (argv[i] == string("-f") && argv[i + 1] == string("linear")) {
				continue; // sthn periptwsh poy yparxei MONO linear sto command line meta to -f synexizoume.
			}
			cout << "No filter found. Please type the correct input." << endl; // den mphke filtro.
			system("PAUSE");
			return 1;
		}

		if (argv[i] == string("-f") && argv[i+1] != string("linear")) {
			if (argv[i] == string("-f") && argv[i + 1] == string("gamma")) {
				continue; // sthn periptwsh poy yparxei MONO gamma sto command line meta to -f synexizoume.
			}
			cout << "No filter found. Please type the correct input." << endl; // den mphke filtro.
			system("PAUSE");
			return 1;
		}

		if (argv[i] == string("gamma")) {
			float g = atof(argv[i+1]);
			if (g >= 0.5 && g <= 2.0) {
				cout << "g is: " << g << endl;
				FilterGamma gfiltro = FilterGamma(g);
				item = gfiltro.operator<<(item);
			}
			else {
				cout << "gamma must be >= 0.5 and <= 2.0." << endl;
				system("PAUSE");
				return 1;
			}
		}
		if (argv[i] == string("linear")) {
			i++;
			for (int j = 0; j < 3; j++) {
				a[j] = atof(argv[i]); // eisagoume ta xyz tou a.
				i++; // ayxanoume to deikth gia na paroume ola ta stoixeia.
			}
			for (int j = 0; j < 3; j++) {
				c[j] = atof(argv[i]); // eisagoume ta xyz tou c.
				i++; // ayxanoume to deikth gia na paroume ola ta stoixeia.
			}
			for (int j = 0; j < 3; j++) {
				cout << "a elements: " << a[j] << endl;
				cout << "c elements: " << c[j] << endl;
			}
			Color alpha = Color(a[0], a[1], a[2]); // bazoume ta stoixeia se 3d vectors.
			Color charlie = Color(c[0], c[1], c[2]);
			FilterLinear grammiko = FilterLinear(alpha, charlie); 
			item = grammiko.operator<<(item); // apply filter.
		}
	}
	item.save(ref2, form);
	delete[] a;
	delete[] c; // apodesmeyoume th mnhmh twn 2 pinakwn.
	system("PAUSE");
	return 0;
}