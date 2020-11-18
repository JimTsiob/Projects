import java.util.*;
import java.io.*;

public class Fmc{
    public static Catalog get_cat(String file, String type){
        Catalog prev_cat = new Catalog();
        BufferedReader reader = null;
        String line;
        boolean is_list = false;
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                LinkedList<String> _p = new LinkedList<>();
                String _list = "";
                String[] info = line.toUpperCase().split(",");
                for(String i: info){
                    // Get info. First see if is list of strings. If not just add info to (_p)
                    if(i.contains("{")){
                        is_list = true;
                        i = i.replace("{", "");
                    }
                    if(is_list){
                        if(i.contains("}")){
                            is_list = false;
                            i = i.replace("}", "");
                            _list += i;
                            _p.add(_list);
                            _list = "";
                        }else{
                            _list += i + ",";
                        }
                    }else{
                        _p.add(i);
                    }
                }
                // Create object
                if(_p.get(0).equals("MOVIE") && type.equals("MOVIE")){
                    boolean _isBr = (_p.get(9).equals("TRUE")) ? true : false;
                    boolean _isLatestRelease = (_p.get(10).equals("TRUE")) ? true : false;
                    Movie _movie = new Movie(_p.get(1), _p.get(2).split(","), _p.get(3), _p.get(4).split(","), _p.get(5).split(","), _p.get(6).split(","), _p.get(7).split(","), _p.get(8), _isBr, _isLatestRelease);
                    prev_cat.add(_movie, Integer.parseInt(_p.get(11)));
                }else if(_p.get(0).equals("GAME") && type.equals("GAME")){
                    Game _game = new Game(_p.get(1), _p.get(2).split(","), _p.get(3), _p.get(4).split(","), _p.get(5));
                    prev_cat.add(_game, Integer.parseInt(_p.get(6)));
                }
            }
        } catch(IOException e){}
        return prev_cat;
    }

    public static CatalogEni get_cateni(String file, Catalog catMovie, Catalog catGame){
        CatalogEni prev_cateni = new CatalogEni();
        BufferedReader reader = null;
        String line;
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null){
                LinkedList<String> _peni = new LinkedList<>();
                String[] info = line.toUpperCase().split(",");
                for(String i: info){
                    _peni.add(i);
                }
                Product _p = new Product("_name", new String[1], "_productionYear", new String[1]);
                // Game
                if(_peni.get(0).equals("CATGAME")){
                    for(Product p: catGame.getArray()){
                        if(p.getName().equals(_peni.get(1))){
                            _p = p;
                        }
                    }
                    boolean _isWeek = (_peni.get(6).equals("TRUE")) ? true : false;
                    Enikiasi _eni = new Enikiasi(catGame, _p, _peni.get(2).split(" "), _peni.get(3), Integer.parseInt(_peni.get(4)), Integer.parseInt(_peni.get(5)), _isWeek);
                    prev_cateni.add(_eni);
                // Movie
                }else if(_peni.get(0).equals("CATMOVIE")){
                    for(Product p: catMovie.getArray()){
                        if(p.getName().equals(_peni.get(1))){
                            _p = p;
                        }
                    }
                    boolean _isWeek = (_peni.get(6).equals("TRUE")) ? true : false;
                    Enikiasi _eni = new Enikiasi(catMovie, _p, _peni.get(2).split(" "), _peni.get(3), Integer.parseInt(_peni.get(4)), Integer.parseInt(_peni.get(5)), _isWeek);
                    prev_cateni.add(_eni);
                }
            }
        } catch(IOException e){}
        return prev_cateni;
    }

    public static void save(String filename, CatalogEni catEni){
        File f = new File(filename);
        String text = "";
        try{
            FileWriter writer = new FileWriter(f);
            for(Enikiasi _eni:catEni.getArray()){
                if(_eni.getCat().get(0) instanceof Game) text += "catGame,";
                else text += "catMovie,";
                text += _eni.getEniProduct().getName() + ",";
                text += _eni.getNp() + ",";
                text += _eni.getDate() + ",";
                text += _eni.getTime() + ",";
                text += _eni.getCostOne() + ",";
                text += _eni.getWeek() + "\n";
            }
            writer.write(text);
            writer.close();
        }catch(IOException e){}
    }
}