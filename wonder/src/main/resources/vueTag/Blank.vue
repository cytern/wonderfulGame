<script type="text/jsx">
  import mixins from './question.mixins'

  export default {
    mixins: [mixins],
    name: 'Question-Blank',
    // functional: true,
    props: {
      q: {
        type: Object,
        default: function() {
          return {
            'type': 'blank',
            'Questions': '19-23',
            'Description': 'Complete the sentence below. Complete the following summary of the paragraphs of Reading Passage, using <b>no more than two words</b> from the Reading Passage for each answer. Write your answers in boxes 19-24 on your answer sheet',
            //填空题的输入框会随机出现在文本前后或者中间
            'subject': 'The most important step is for the school authorities to produce a <a-input v-model="35" answer="towards bullying" /> which makes the school\'s attitude towards bullying quite clear. It should include detailed 36  <a-input v-model="36" answer="include"/> as to how the school and its staff will react if bullying occurs. In addition, action can be taken through the  <a-input v-model="37" answer="taken through" />This is particularly useful in the early part of the process, as a way of raising awareness and encouraging discussion. On its own, however, it is insufficient to bring about a permanent solution.<br/> Effective work can also be done with individual pupils and small groups. For example, potential 38  <a-input v-model="38"/>of bullying can be trained to be more self-confident. Or again, in dealing with group bullying, a \'no blame\' approach, which avoids confronting the offender too directly, is often effective. Playground supervision will be more effective if members of staff are trained to recognize the difference between bullying and mere 39  <a-input v-model="39"/>.'
            // 'subject': [
            //   {
            //     'number': 19,
            //     'label': 'Farmers was first founded as <a-input/> in Auckland by Mr Laidlaw', //select 是占位符 里面为空
            //     'answer': 'Florida'
            //   },
            //   {
            //     'number': 21,
            //     'label': 'During oversea expansion, Farmers set up  <a-input/>  in cities such as London',
            //     'answer': 'essential use'
            //   },
            //   {
            //     'number': 22,
            //     'label': 'Farmers developed fast and bought one <a-input/>  then',
            //     'answer': 'co-authored'
            //   },
            //   {
            //     'number': 23,
            //     'label': 'Pots for transporting liquids would have held no more than about <a-input/>  litres',
            //     'answer': '20'
            //   }
            // ]
          }
        }
      }
    },
    data() {
      return {
        qa: {}
      }
    },
    render() {
      const { q } = this.$props
      const {} = this.$data
      let vnodes = []
      vnodes.push(<h1>{q.Questions}</h1>)
      vnodes.push(this.$createElement(
        'div',
        {
          domProps: { innerHTML: q.Description },
          attrs: { class: 'Description' }
        },
        []
      ))
      if (Array.isArray(q.subject)) {
        this.arraySubject(q.subject, vnodes)
      } else {
        this.singleSubject(q.subject, vnodes)
      }
      return this.$createElement('div', {}, vnodes)
    },
    methods: {
      // 单行文本生成
      singleSubject(subject, vnodes = []) {
        let start = 0 // 从0开始
        let input = subject.match(/\<a\-input.+?\/\>/g)
        let children = []
        input.forEach((item) => {
          let end = subject.indexOf(item) // 获取当前的位置 获取字符串span
          item.replace("\\n","")
          let exec = /\<a\-input v\-model=\"(\d+)\"\/>/.exec(item)
          if (exec) {
            console.log("成功的打印为 " + item);
            let number = exec[1] // 截取当前的序号
            children.push(this.$createElement('span', { domProps: { innerHTML: subject.slice(start, end) } }))
            children.push(this.$createElement('a-input', {
              class: {
                'blank-input': true
              },
              attrs: {
                placeholder: number,
                style: 'width:140px;',
                size: 'small',
              },
              on: {
                change: (evt) => {
                  let value = evt.target.value
                  this.qa[number].value = value
                  this.handleChange(number, value, answer)
                }
              }
            }, null))
            this.qa[number] = {
              value: undefined
              // answer: answer
            }
            start = end + item.length
          } else {
            console.log( "打印数据   " +item   +"判断值"  + exec );
            throw  new Error('解析数据错误，请联系管理员')
          }
        })
        vnodes.push(this.$createElement('div', { 'class': { 'question-children-item': true } }, children))
      },
      // 数组生成
      arraySubject(subject, vnodes) {
        subject.forEach((item) => {
          let label = item.label
          let labels = label.split(/<a-input.+>/gi)
          let number = item.number
          let answer = item.answer
          let children = [<span>{number}. </span>]
          this.qa[number] = { value: undefined, answer: answer }
          labels.forEach((item, len) => {
            children.push(<span>{item}</span>)
            if (len !== labels.length - 1) {
              children.push(this.$createElement('a-input', {
                attrs: {
                  class: 'blank-input',
                  placeholder: number,
                  style: 'width:100px;'
                },
                on: {
                  change: (value) => {
                    this.qa[number].value = value
                  }
                }
              }, null))
            }
          })
          vnodes.push(this.$createElement('div', { 'class': { 'question-children-item': true } }, children))
        })
      }
    }
  }
</script>
<style lang="less" scoped>
  .Description {
    margin-bottom: 10px;
  }

  .blank-input {
    margin-left: 10px;
    margin-right: 10px;
  }

  .question-children-item {
    line-height: 32px;
    margin: 5px 0;
  }
</style>
