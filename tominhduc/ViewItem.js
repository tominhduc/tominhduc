import React from 'react';
import { FlatList,Image, View, Text, TouchableOpacity, StyleSheet,Alert } from 'react-native';
import Tablist from './Tablist';

export class ViewItem extends React.Component {
  constructor(props){
    super(props);
    this.state={
      isLoading:true,

  }

  }

    componentDidMount() {
      const GETIMG = "http://192.168.1.29/duccosodulieu/getimgtruyen.php?id="+this.props.navigation.getParam('id');
          fetch(GETIMG, {
                  method: 'GET',
                  headers: {
                      'Accept': 'application/json',
                      'Content-Type': 'application/json',
                  }
              }).then((response) => response.json())
                  .then((responseData) => {
                      this.setState({
                          isLoading: false,
                          dataSource: responseData,
                      }, function () {

                      });


                  }).catch(function (err) {
                      Alert.alert(GETIMG);
                      console.log(err);
                  }).done();
      }
    render() {
      return (
        <View>
          <FlatList
            data={this.state.dataSource}
            renderItem={({item})=>(
              <View >
                <View>
                <Image
                   style={{width: 100, height: 100}}
                   source={{uri: 'http://192.168.1.29/duccosodulieu'+item.image}}
                 />
                </View>
              </View>

            )}

          />
        </View>
        );
    }
}
