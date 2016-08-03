var MenuBox = React.createClass({
	getInitialState : function() {
		return {
			menus: [
			{id:0, text:"/lib/react-15.1.0/sample/todo", style:(location.href.indexOf("/todo")>-1?{color: 'red'}:{}) },
			{id:1, text:"/lib/react-15.1.0/sample/hide", style:(location.href.indexOf("/hide")>-1?{color: 'red'}:{}) },
			]
		};
	},
	onGo : function(e) {
		//var nodes = Array.prototype.slice.call( e.currentTarget.children );
        //var index = nodes.indexOf( e.target );
		//console.log(e.currentTarget);
		//console.log(this.state.menus[e.target.value]);
		location.href=this.state.menus[e.target.value].text+".html";
	},
	
	render: function() {
		var menus = this.state.menus.map(
			(menu) => {				
				return <li key={menu.id} onClick={this.onGo} value={menu.id} style={menu.style}>{menu.text}</li>;
			}
		);
		return (
			<div class="div_menu">
			<ul>
			{menus}
			</ul>
			</div>
		);
	}
}); 

ReactDOM.render(<MenuBox />, document.getElementById('div_menu'));