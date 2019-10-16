import React,{Component} from 'react';
import {createAppContainer} from 'react-navigation';
import {createStackNavigator} from 'react-navigation-stack';
import {
  View,
  TouchableOpacity,
  FlatList,Image,
  StyleSheet,
  Text,Alert
} from 'react-native';
import { ViewItem } from './ViewItem'
class Tablist extends Component{
  static navigationOptions = {
      title: 'truyentranh.com',
    };
  constructor(props){
    super(props);
    this.state={
      isLoading:true,
  }
  }

  componentDidMount() {
        fetch("http://192.168.1.29/duccosodulieu/truyenall.php", {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            }).then((response) => response.json())
                .then((responseData) => {
                    this.setState({
                        isLoading: false,
                        dataSource: responseData
                    }, function () {

                    });
                    console.log(responseData);
                }).catch(function (err) {
                    Alert.alert("Đã có lỗi xảy ra...");
                    console.log(err);
                }).done();
    }
  render(){
  return (
    <View style={styles.container}>
      <FlatList
        data={this.state.dataSource}
        renderItem={({item})=>(
          <TouchableOpacity onPress={() => this.props.navigation.navigate('ViewItem',
          {id : item.id}
        )}>
          <View style={styles.duongVien}>
            <View>
            <Text style={styles.styleItem}>{item.name}</Text>
            <Text style={styles.styleItem}>{item.tacgia}</Text>
            </View>
          </View>
          </TouchableOpacity>
        )}

      />
    </View>
  );
}}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: 20,
  },

  duongVien:{
    flex:1,
    flexDirection:'row',
    borderBottomWidth:2,
    borderBottomColor:'black'
  },

  styleItem:{
    color:'black',
    margin:10,
    fontSize:20
  },
});

const HomeStack = createStackNavigator({
  Tablist:Tablist,
  ViewItem:ViewItem
  });
export default createAppContainer(HomeStack);
